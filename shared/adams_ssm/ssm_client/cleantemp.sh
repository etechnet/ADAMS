#!/bin/sh

cd java_output
ls > ../rimuovere.tmp
rm -f *
cd ..
for file in `cat rimuovere.tmp`
do
	rm -f $file
done

rm -f rimuovere.tmp

