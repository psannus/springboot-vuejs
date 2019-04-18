#!/bin/bash
cd ..
cd client
yarn build
rm vue.zip
zip -r vue.zip dist
scp -i ./../server/trackify.pem vue.zip ec2-user@ec2-3-92-62-1.compute-1.amazonaws.com:~/
ssh -i ./../server/trackify.pem ec2-user@ec2-3-92-62-1.compute-1.amazonaws.com 'bash -s' <<'ENDSSH'
    unzip -o vue.zip
ENDSSH