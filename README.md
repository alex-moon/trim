Trim
====

Jersey and Tomcat: RESTful Services Made Easy

Trim is a very simple Jersey app that reads texts and calculates term correlations. It is best used in conjunction with (Jaws)[https://www.github.com/alex-moon/jaws/].

Setup and Deploy
----------------

See the Jaws readme for details. The only difference with Trim is that you will want to bump up your heap space.

On your local Tomcat, edit your ``JAVA_OPTS`` in ``/etc/default/tomcat7`` thus::

    -Xmx2048m -XX:+CMSIncrementalMode -XX:+UseConcMarkSweepGC

On Elastic Beanstalk::

1. Go to **Configuration**
2. Go to **Software Configuration**
3. Edit your **Maximum JVM heap size** appropriately (see your EC2 instance details at [http://aws.amazon.com/ec2/pricing/] for your upper limit - use all the memory you have).

