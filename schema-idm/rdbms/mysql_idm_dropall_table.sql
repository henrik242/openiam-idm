 mysql -u idmuser openiam -e "show tables" | grep -v Tables_in | grep -v "+" | \
gawk '{print "drop table " $1 ";"}' | mysql -u idmuser openiam
