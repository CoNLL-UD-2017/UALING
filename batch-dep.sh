#!/bin/bash

for i in UD_*
do
	echo $i
	cd $i

	ln -s ../unling-conll2017.jar . 

        for j in *-ud-train.conllu
        do
                echo $j
		# $k = dev file
                k=`echo $j | sed 's/-train/-dev/g'`
                ls -al $k
		# $l = result relation files
                l=`echo $j | sed 's/train\.conllu/rel/g'`
                echo $l

                java -cp unling-conll2017.jar MakeRel $j > $j".rel"
                java -cp unling-conll2017.jar MakeRel $k > $k".rel"
                cat $k".rel" | sort | uniq -c > $k".rel.uniq"
                cat *.rel | sort | uniq > $l
                java -cp unling-conll2017.jar CalcRELVecSimil $l $k".rel.uniq" $j 0.1  > $j".3"
        done

	cd ..
done
