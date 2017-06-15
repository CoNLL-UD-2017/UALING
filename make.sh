for i in *.class
do
	echo $i
	j=`echo $i | sed 's/\.class/.java/g'`
	cp ../$j . 
done
