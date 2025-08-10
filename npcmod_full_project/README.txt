NPC Follower Mod (Forge 1.8) - Codespaces-ready project skeleton
---------------------------------------------------------------

What is included:
- Source files under src/main/java/com/example/npcmod
- A simple follower skin at src/main/resources/assets/npcmod/textures/entity/follower.png
- build.gradle and settings.gradle configured for Forge 1.8 (example)
- NOTE: This archive does NOT include the Forge MDK files or Gradle wrapper binary due to redistribution/licensing and size.

To build in GitHub Codespaces or locally, follow these steps:

1) Option A — GitHub Codespaces (recommended)
  a. Create a new GitHub repository and upload the contents of this ZIP.
  b. Open the repo in GitHub Codespaces.
  c. Open the Codespaces terminal. Install OpenJDK 8 if not present:
     sudo apt update && sudo apt install -y openjdk-8-jdk
  d. Download the Forge 1.8 MDK package (from files.minecraftforge.net) into the workspace root.
     For example, in terminal run (replace URL with the official Forge 1.8 MDK link):
     wget https://files.minecraftforge.net/maven/net/minecraftforge/forge/1.8-11.14.4.1577/forge-1.8-11.14.4.1577-mdk.zip
  e. Unzip the MDK into the workspace (this will provide gradle wrapper and required scripts):
     unzip forge-1.8-11.14.4.1577-mdk.zip -d forge_mdk
     cp -r forge_mdk/* .
  f. Run the setup (in Codespaces; this may take several minutes):
     ./gradlew setupDecompWorkspace
     ./gradlew --refresh-dependencies
     ./gradlew build
  g. After build, the mod JAR will be in build/libs/

2) Option B — Local machine
  - Install Java 8 and Gradle or use the Gradle wrapper from the Forge MDK.
  - Download Forge MDK for 1.8 and follow same steps as above.
  - Run ./gradlew setupDecompWorkspace and ./gradlew build

Notes & Troubleshooting:
- The example uses ForgeGradle and a recommended 1.8 build 11.14.4.1577; if that build is unavailable, use a compatible 1.8 MDK and adjust build.gradle.
- The EntityOtherPlayerMP constructor usage in source may need adjustments depending on mappings; if compile errors occur, check imports and server instance parameters.
- If you want, I can also create a GitHub repo with this content for you, then guide you through Codespaces build step-by-step.

Security note: I did not include Forge MDK binaries to avoid redistribution issues. You must download the official MDK from the Forge website.
