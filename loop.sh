for i in `seq 1 1000`
do
  echo "${i}回目のループ"
  curl http://localhost:9000
done
