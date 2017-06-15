#!/bin/bash

### /udpipe-1.1.0-bin/udpipe


for i in `find $PWD -name "*-ud-train.conllu.2"` `find $PWD -name "*-ud-train.conllu.3"` `find $PWD -name "*-ud-train.conllu.4"` 
do
	echo $i
	j=`echo $i | sed 's/-train\.conllu\../-test.conllu/g'` 

	#if [ -f $j ]
  	#then
    	#	echo "/home/u26/jungyeul/udpipe-1.1.0-bin/udpipe --train --tokenize --tag --parse  --heldout="$j " "  $i".udpipe "  $i >>$i".sh" 
	#else
	udpipe --train --tokenize --tag --parse   $i.udpipe  $i 
	#fi
	udpipe --accuracy  --tag --parse  $i.udpipe  $j  >  $i.score
done

