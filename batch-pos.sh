#!/bin/bash

for i in UD_*
do
	echo $i
	cd $i

	ln -s ../unling-conll2017.jar .

        for j in *-ud-train.conllu
        do
                echo $j
                k=`echo $j | sed 's/-train/-dev/g'`
                ls -al $k
                l=`echo $j | sed 's/train\.conllu/pos/g'`
                echo $l

                java -cp unling-conll2017.jar MakeTriPOS $j > $j".pos"
                java -cp unling-conll2017.jar MakeTriPOS $k > $k".pos"
                cat $k".pos" | sort | uniq -c > $k".pos.uniq"
                cat *.pos | sort | uniq > $l
                java -cp unling-conll2017.jar CalcPOSVecSimil $l $k".pos.uniq" $j 0.1  > $j".2"
        done

	cd ..
done
