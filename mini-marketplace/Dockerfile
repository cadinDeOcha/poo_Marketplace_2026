FROM maven:3.9-eclipse-temurin-21-noble

ENV DEBIAN_FRONTEND=noninteractive
ENV DISPLAY=:99

WORKDIR /app

RUN apt-get update && apt-get install -y \
    xvfb \
    x11vnc \
    novnc \
    websockify \
    libgtk-3-0t64 \
    libgl1 \
    libgl1-mesa-dri \
    libasound2t64 \
    libxtst6 \
    libxi6 \
    fonts-dejavu-core \
    x11-utils \
    && rm -rf /var/lib/apt/lists/*

COPY pom.xml .

RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B clean test

RUN mvn -B compile

COPY start.sh /start.sh

RUN sed -i 's/\r$//' /start.sh \
    && chmod +x /start.sh

EXPOSE 6080

CMD ["/start.sh"]
