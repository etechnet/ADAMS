#!/bin/bash

for f in $*
do
	echo "Mod. $f"
	cat ~/adams/head.c++-tpl $f >$f.tmp
	cp -f $f.tmp $f
done