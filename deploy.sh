mvn clean install

docker build -t articles-rep .
docker tag articles-rep:latest 128556667010.dkr.ecr.eu-central-1.amazonaws.com/articles-rep:latest
docker push 128556667010.dkr.ecr.eu-central-1.amazonaws.com/articles-rep:latest

v="aws ecs list-tasks --cluster \"default\" --output text --query taskArns[0]"
if  [ "$(eval "$v")" != "None" ]; then
    aws ecs stop-task --task $(eval "$v") > stop.txt
fi
aws ecs run-task --cluster "default" --task-definition MyTask > run.txt
