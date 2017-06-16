#!/bin/bash

for o in *-sample.conllu
do
        java -cp unling-conll2017.jar MakeTriPOS $o > $o".pos" ; cat $o".pos" | sort | uniq -c > $o".pos.uniq"

        for i in UD_*
        do
        	echo $i
        	cd $i

                for j in *-ud-train.conllu
                do
                        echo $j
                        l=`echo $j | sed 's/train\.conllu/pos/g'`

                        cat ../$o".pos" $j".pos" | sort | uniq > $l
                        java -cp unling-conll2017.jar CalcPOSVecSimil $l ../$o".pos.uniq" $j 0.3  >> ../$o".2"
                done
        	cd ..
        done
done


