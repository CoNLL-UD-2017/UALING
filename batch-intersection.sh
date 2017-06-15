#!/bin/bash

for i in UD_*
do
	echo $i
	cd $i

	ln -s ../unling-conll2017.jar .

        for j in *-ud-train.conllu
        do
		ls -al $j".2" $j".3"
                java -cp unling-conll2017.jar SimilarityIntersection $j".2" $j".3" > $j".4"
        done

	cd ..
done


