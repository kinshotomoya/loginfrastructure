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
  - fluentd -> kafka -> fluentd -> elasticsearchの流れ（下図参考）

![kafka_fluentd_-_Google_検索](https://user-images.githubusercontent.com/58924623/77252278-1ddae380-6c96-11ea-8436-e409a8fe79b5.png)



## 条件
- playからは、docker logging driverでログを送る
  - docker-logging-driverを使いたいので、playもコンテナ化する
- kubernetesの練習のため、本番はkubernetesにのせる


## 今後
AWSリソースも使う
  - s3
  - kinesis
  - kafka(MSK)など 

