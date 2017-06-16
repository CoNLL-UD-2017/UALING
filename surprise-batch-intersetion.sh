#!/bin/bash

for i in  *-sample.conllu.2
do
	echo $i

	j=`echo $i | sed 's/\.2/.3/g'`
       	k=`echo $i | sed 's/\.2//g'`

        java -cp unling-conll2017.jar SimilarityIntersection $i $j > $k".4"
done

