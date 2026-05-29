# Tahap 1: Membangun (Build) aplikasi menggunakan Maven
FROM maven:3.9.4-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compile kode menjadi file .jar (tanpa menjalankan unit test agar cepat)
RUN mvn clean package -DskipTests

# Tahap 2: Menjalankan aplikasi
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
# Ambil file .jar dari tahap 1
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8082
# Perintah saat container dinyalakan
ENTRYPOINT ["java", "-jar", "app.jar"]