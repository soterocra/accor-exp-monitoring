# Enter /tmp directory
cd /tmp/

# Install Java 11:
sudo amazon-linux-extras install java-openjdk11 -y

# Install chrome driver
sudo wget https://chromedriver.storage.googleapis.com/103.0.5060.53/chromedriver_linux64.zip
sudo unzip chromedriver_linux64.zip
sudo mv chromedriver /usr/bin/chromedriver
chromedriver --version

# Install chrome
sudo yum install -y https://dl.google.com/linux/chrome/rpm/stable/x86_64/google-chrome-stable-103.0.5060.114-1.x86_64.rpm


# Download Selenium Grid 4.3.0
wget "https://github.com/SeleniumHQ/selenium/releases/download/selenium-4.3.0/selenium-server-4.3.0.jar" -O selenium-grid.jar

# Execute Selenium
nohup java -jar selenium-grid.jar standalone --session-timeout 60 > selenium.log 2>&1 &