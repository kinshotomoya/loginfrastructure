# loginfrastructure
ログ基盤作る


## 構成
- scala(play)
- nginx
- fluentd
  - forwarder node
  - aggregator node
- elasticsearch
  - production用のes
  -ログ用のes
- kibana
- kafka

## 条件
- playからは、docker logging driverでログを送る
  - docker-logging-driverを使いたいので、playもコンテナ化する


## 今後
AWSリソースも使う
  - s3
  - kinesis
  - kafka(MSK)など 

