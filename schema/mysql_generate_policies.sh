#!/bin/bash

SCRIPT="result.sql"
SCRIPT_EXT=".groovy"
SCRATCH="__scratch"

# Argument 1 is the destination file; use default if not set
if [ -n "$1" ]; then
  SCRIPT=$1
fi


echo 'USE openiam;' > $SCRIPT
for GS in `find -name "*$SCRIPT_EXT"`
do
  GS_NAME=$(basename $GS $SCRIPT_EXT)

  dos2unix -q -n $GS $SCRATCH
  echo >> $SCRATCH

  GS_TEXT=$(cat $SCRATCH | while read line; do echo -n "$line\r\n"; done | sed 's#\"#\\"#g' | sed "s#'#\\\\'#g" )
  echo "UPDATE POLICY SET RULE='$GS_TEXT' WHERE NAME='$GS_NAME';" >> $SCRIPT
done
echo 'commit;' >> $SCRIPT

rm $SCRATCH