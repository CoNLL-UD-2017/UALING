#!/bin/bash

for o in *-sample.conllu
do
	java -cp unling-conll2017.jar  MakeRel $o > $o".rel" ; cat $o".rel" | sort | uniq -c > $o".rel.uniq"

	for i in UD_*
	do
		echo $i
		cd $i

		for j in *-ud-train.conllu
		do
			echo $j
			l=`echo $j | sed 's/train\.conllu/rel/g'`

			cat ../$o".rel" $j".rel" | sort | uniq > $l
			java -cp unling-conll2017.jar  CalcRELVecSimil $l ../$o".rel.uniq" $j 0.9  >> ../$o".3"
		done

		cd ..
	done
done


