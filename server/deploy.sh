#!/bin/bash
scp -i trackify.pem ./build/libs/docker/trackify.jar ec2-user@ec2-3-92-62-1.compute-1.amazonaws.com:~/
