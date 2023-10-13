![Zune API Header](https://github.com/hynamedev/ZuneAPI/assets/89894310/761bd3c6-b8a3-4050-8fa1-26377d3f6949)
This project is an ongoing effort to recreate the backend API of the now-defunct Zune music service. Please note that this is a **work in progress** and nowhere ready for production. Zune was a media software and streaming service developed by Microsoft, which was discontinued in ~2011. This project is an attempt to bring back parts of the functionality of the Zune backend API.

## Table of Contents

- [Getting Started](#getting-started)
- [Dependencies](#dependencies)
- [Contributing](#contributing)
- [License](#license)

## Getting Started

To get started with this Java project, follow these steps:

1. Clone the repository:

   ```bash
   git clone https://github.com/hynamedev/ZuneAPI.git
   cd ZuneAPI
   ```

2. Start & setup your Mongo Database
   ```bash
   sudo apt-get install -y mongodb-org
   sudo systemctl start mongod
   ```
   
3. Move artist images into the `/images` folder
   ```bash
   sudo mkdir /images
   sudo mv /path/to/images/* /images
   ```

4. Build Server using Maven & run
   ```bash
   mvn package
   java -jar target/ZuneAPI-1.0-SNAPSHOT.jar
   ```


## Dependencies

The project relies on various Java libraries and technologies, including but not limited to:

- Spark: Web framework thingy.
- JAXB: XML Serialization and stuff
- MongoDB-Java-Driver: For data storage and retrieval from a database.
- Lombok: makes this easier

## Contributing

Contributions are welcome! Please feel free to submit a pull request or open an issue if you have any questions or suggestions.

## License

This Java project is licensed under the [MIT License](LICENSE). Feel free to use, modify, and distribute it as per the terms of the license. However, please keep in mind that this project is an unofficial recreation of the Zune backend API and is not associated with or endorsed by Microsoft.

**Disclaimer:** The use of this project to create nuclear or chemical weapons of any kind is strictly prohibited.
