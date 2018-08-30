
**DOCKER**

Docker is an open source software platform to create, deploy and manage virtualized application containers on a common operating system (OS), with an ecosystem of allied tools.
 
A container is a virtual walled environment for your application. It’s literally a ‘container’ inside the host OS. Thus your application works like it is in its own self-contained environment, but it’s actually sharing operating system resources of the host computer.  Because of this, containers are more resource efficient than full blown virtual machines. You get more bang for your buck running a bare metal machine with a bunch of containers than you do running a bare metal machine with a bunch of VMs. This is why massive cloud computing companies running 10’s of thousands of servers are running containers. Google, Facebook, Netflix, Amazon are all big advocates of containers.

A Dockerfile is a text file that contains a list of commands that the Docker daemon calls while creating an image

I like the ability of how I can package my applications so easily with Docker. I’ve been living in the Java land for 15+ years. It truly enables “Write Once, Run Anywhere” or WORA. This allows you to package an application as an archive (JAR, WAR or EAR) and that would run wherever the JVM is.

With Docker, I extract myself to a further higher level which says, hey, package application once and deploy it anywhere, wherever Docker Engine is running. Similar to WORA, I call this PODA or “Package Once, Deploy Anywhere.” That results in a higher level extraction where it becomes language-agnostic. It doesn’t matter whether I’m running Java, PHP, Ruby, Scala, pick a language, I can create a package and every Docker engine will understand the same Docker image. I think that to me is the most fascinating aspect and that’s the part that I love.


**Comands to run the demo**
```
mvn clean install
docker build -f docker -t "vishwasm14/devops-metadata" .
docker login 
docker push vishwasm14/devops-metadata

docker network create spring_mongo_net
docker run --name mongo-db --network=spring_mongo_net -p 27017:27017 -v /home/vishwas/mongo-data:/data/db -d mongo
docker run -d --name devops-metadata --network=spring_mongo_net -p 8080:8888  vishwasm14/devops-metadata
```


**INSTALL DOCKER IN LINUX** 

- sudo apt-get update
- sudo apt-get install docker-ce
- sudo docker run hello-world

**Basic Commands**

```$ docker pull alpine ```

The pull command fetches the alpine image from the Docker registry and saves it in our system
you are going to run an Alpine Linux container (a lightweight linux distribution) 


```
docker run alpine ls -l 


total 48
drwxr-xr-x    2 root     root          4096 Mar  2 16:20 bin
drwxr-xr-x    5 root     root           360 Mar 18 09:47 dev
drwxr-xr-x   13 root     root          4096 Mar 18 09:47 etc
drwxr-xr-x    2 root     root          4096 Mar  2 16:20 home
drwxr-xr-x    5 root     root          4096 Mar  2 16:20 lib

```

where ls -l is command is passed to docker run command.


- What happened? Behind the scenes, a lot of stuff happened. When you call run,
-  The Docker client contacts the Docker daemon
- The Docker daemon checks local store if the image (alpine in this case) is available locally, and if not, downloads it from Docker Store. (Since we have issued docker pull alpine before, the download step is not necessary)
- The Docker daemon creates the container and then runs a command in that container.
- The Docker daemon streams the output of the command to the Docker client


``` $ docker run alpine echo "hello from alpine" ```

hello from alpine

```$ docker run alpine /bin/sh ```

Wait, nothing happened! Is that a bug? Well, no. These interactive shells will exit after running any scripted commands, unless they are run in an interactive terminal 

``` $ docker run -it alpine /bin/sh. ```

``` $ docker ps ```     -- command shows you all containers that are currently running.

``` $ docker ps -a ```  -- What you see above is a list of all containers that you ran.


**Docker terminalogies**

- Images        - The file system and configuration of our application which are used to create containers
- Containers    - Running instances of Docker images
- Docker daemon - The background service running on the host that manages building, running and distributing Docker containers.
- Docker client - The command line tool that allows the user to interact with the Docker daemon.
- Docker Store -  A registry of Docker images, where you can find trusted and enterprise ready containers, plugins, 
and Docker editions


**show running container + stoping + deleting containers**

```
$ docker ps 

CONTAINER ID        IMAGE                  COMMAND                  CREATED             STATUS              PORTS               NAMES
a7a0e504ca3e        dockersamples/static-site   "/bin/sh -c 'cd /usr/"   28 seconds ago      Up 26 seconds       80/tcp, 443/tcp     stupefied_mahavira
 
$ docker stop a7a0e504ca3e  - Stop conatainer with CONTAINER_ID

$docker rm   a7a0e504ca3e   - Remove/delete the container with CONTAINER ID

```

**Running docker with many option: -** 

```
$ docker run --name static-site -e AUTHOR="vishwas" -d -P dockersamples/static-site 

```

In the above command:

- d will create a container with the process detached from our terminal
- P will publish all the exposed container ports to random ports on the Docker host
- e is how you pass environment variables to the container
- name allows you to specify a container name

AUTHOR is the environment variable name and Your Name is the value that you can pass

**docker port** 

```
$ docker port static-site
443/tcp -> 0.0.0.0:32772
80/tcp -> 0.0.0.0:32773 

```

**Build the image from  the docker file**

you have writte the docker file, run below command to create the image.

``` 
$ docker build -t vishwas/myfirstapp 
```

The docker build command is quite simple - it takes an optional tag name with the -t flag, 
and the location of the directory containing the Dockerfile - the . indicates the current directory:



**Run your image**

```
$ docker run -p 8888:5000 --name myfirstapp vishwas/myfirstapp 

```

Running on http://0.0.0.0:5000/ (Press CTRL+C to quit)

**Push Docker image to docker Cloud**

```
$ docker login 
```

Enter YOUR_USERNAME and password when prompted.

``` 
$ docker push vishwasm14/myfirstapp 

```


**Dockerfile commands summary**

Here's a quick summary of the few basic commands we used in our Dockerfile.

- *FROM* : starts the Dockerfile. It is a requirement that the Dockerfile must start with the FROM command. Images are created in layers, which means you can use another image as the base image for your own. The FROM command defines your base layer. As arguments, it takes the name of the image. Optionally, you can add the Docker Cloud username of the maintainer and image version, in the format username/imagename:version.


- *RUN* : is used to build up the Image you're creating. For each RUN command, Docker will run the command then create a new layer of the image. This way you can roll back your image to previous states easily. The syntax for a RUN instruction is to place the full text of the shell command after the RUN (e.g., RUN mkdir /user/local/foo). This will automatically run in a /bin/sh shell. You can define a different shell like this: RUN /bin/bash -c 'mkdir /user/local/foo'


- *COPY* copies local files into the container.


- *CMD* : defines the commands that will run on the Image at start-up. Unlike a RUN, this does not create a new layer for the Image, but simply runs the command. There can only be one CMD per a Dockerfile/Image. If you need to run multiple commands, the best way to do that is to have the CMD run a script. CMD requires that you tell it where to run the command, unlike RUN. So example CMD commands would be:

  CMD ["python", "./app.py"]

  CMD ["/bin/bash", "echo", "Hello World"]


- *EXPOSE* :  creates a hint for users of an image which ports provide services. It is included in the information which can be retrieved via $ docker inspect <container-id>.
Note: The EXPOSE command does not actually make any ports accessible to the host! Instead, this requires publishing ports by means of the -p flag when using $ docker run.

PUSH pushes your image to Docker Cloud, or alternately to a private registry



**delete an images**

``` 
$ docker image rm vishwasm14/gs-spring-boot-docker
or
$ docker rmi vishwasm14/gs-spring-boot-docker

$ docker rmi Imagename1 Imagename2
```


**Purging All Unused or Dangling Images**

``` $ docker system prune
    $ docker system prune -a   
```
- additionally remove any stopped containers and all unused images (not just dangling images), 
add the -a flag to the command:


**DOCKER COMPOSE**






