#!/bin/bash

# -Dconfigで設定ファイルを指定できる
exec bin/logApp -Dconfig.resource=application.conf
