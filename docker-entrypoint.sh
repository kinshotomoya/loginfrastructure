#!/bin/bash

# -Dconfigで設定ファイルを指定できる
# JVMoptionを設定する
exec bin/logApp -Dconfig.resource=application.conf
