# t2gpx
A tool to convert TXT files to GPX files

## Usage
### Get t2gpx 

- Get jar from [releases](https://github.com/miantiao5115/t2gpx/releases) 

- Build jar from source

  Java 8 and Maven >=3.3 are required.

  Download:

  ```bash
  git clone git@github.com:miantiao5115/t2gpx.git
  ```

  Then ,import this maven project to your IDE and build.

  Build:

  ```bash
  mvn package -DskipTests
  ```

### Run t2gpx

When you get a jar file, you can run it with a txt file path, then you will get a corresponding gpx file in the same file path.

eg:

```bash
java -jar t2gpx-1.0.jar C:\User\miantiao\test.txt
```

In the same file path, you can see a gpx file named test.gpx.

## Addition

-  About txt file 

  The first three columns of the TXT file must be latitude, longitude, GPS time in sequence, and the columns must be separated by ',', it also has no column header. eg: [test.txt](https://github.com/miantiao5115/t2gpx/blob/main/src/main/resources/test.txt) 

-  About gpx file

    the gpx file complies with graphhopper schema. eg: [test.gpx](https://github.com/miantiao5115/t2gpx/blob/main/src/main/resources/test.gpx) 

