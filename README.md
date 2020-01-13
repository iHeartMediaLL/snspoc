# Quick Start

Before start, you need to:

1, Create a sns topic on aws, get topic arn

2, Create a IAM user, generate access key and access secret

3, Set those values in application.properties

```
cloud.aws.credentials.accessKey=accessKey
cloud.aws.credentials.secretKey=secretKey
cloud.aws.region.static=region

sns.topicName=topicName
sns.topicArn=topicArn
```
