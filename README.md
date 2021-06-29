# coherence 性能測定用

# パッケージ/クラス説明
## ccs.coherence.keyperform
プロセス単位で特定のキーのPut/Listenを行うためのProducer/Comsumerのメインクラスが入っています。

## ccs.coherence.single.consumer
* ConsBusyGetMain

`welcomes`キャッシュの`samekey`キーにたいしてビジーループで`#get`を行い、性能測定を行います。

* ConsListenerMain

`welcomes`キャッシュにたいしてListenerを設定し、`#entryInserted`,`#entryUpdated`の実行回数を測定を行います。

## ccs.coherence.single.producer
* ProducerMain

`welcomes`キャッシュの`samekey`キーにたいしてビジーループで`#put`を行い、性能測定を行います。

* ProducerRandomKeyMain

`welcomes`キャッシュのランダムなキーにたいしてビジーループで`#put`を行い、性能測定を行います。

# 各種シェル
* comsumer.sh
* producer.sh

それぞれproducer/comsumerをバックグラウンドで起動します。

classpathには`./target/classes`と各種ライブラリのパスを設定してください。

各種ライブラリのパスは`mvn dependency:build-classpath -Dmdep.includeScope=runtime`などで取得してください。

* perform.sh

※GitBash(Git for windows)で動作確認しています。GitBash上でJava、mvnが動作可能であること。確認してください。

usage: usage : ./perform.sh (caseNo) (producerCount) (comsumerCount) [same|each]

producer/comsumerを指定したプロセス数起動します。Put/Listenのキーは`same`指定時、1固定となり、`each`指定時、1～Producerの数の範囲で割り振られます。

起動したプロセスはプロセスグループのkill(`kill -- -(pgid)`)でまとめて停止することができるはずです。

