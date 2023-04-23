# isssue_115574

the spark job recives 3 args:
env - I used local, also on dataproc.
temp_bucket - my temp bucket
table_name - table name where you have permissions to write into it.

for example:

```spark-submit   --class=com.lightricks.isssue_115574.Main  ./target/scala-2.12/braze-export-0.1.17-SNAPSHOT.jar   local idayan-temp my_project.idayan_tests.isssue_115574_local```
