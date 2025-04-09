{ pkgs }: {
  deps = [
    pkgs.chromium
    pkgs.chromedriver
    pkgs.jdk17
    pkgs.maven
  ];

  env = {
    CHROME_BIN = "${pkgs.chromium}/bin/chromium";
    CHROMEDRIVER_PATH = "${pkgs.chromedriver}/bin/chromedriver";
  };
}