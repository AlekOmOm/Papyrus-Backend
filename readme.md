
# using AlekOmOm package 

## Prerequisites
Maven: Ensure Maven is installed and configured on your system.
GitHub Personal Access Token (PAT): You’ll need a PAT with read:packages permission to access the library.


## 1. Configure Maven to Authenticate with GitHub Packages

- You’ll need to configure Maven to authenticate with GitHub Packages using your PAT.

### 1. Locate your Maven settings.xml file:

- On Windows: 
  - `C:\Users\<YourUsername>\.m2\settings.xml`
- On Linux/macOS: 
  - `~/.m2/settings.xml`

If the file doesn’t exist, create a new one.

### 2. Add the following to your settings.xml:

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>YOUR_GITHUB_USERNAME</username>
            <password>${env.GITHUB_TOKEN}</password> <!-- Or replace with your PAT directly -->
        </server>
    </servers>
</settings>
```

### 3. PAT with read:packages permission to access the library.
- Replace YOUR_GITHUB_USERNAME with your GitHub username.
- Set system env. variable: `GITHUB_TOKEN`  to your PAT. Alternatively, you can place the PAT directly in the <password> field.
  - fx. token: `ghp_1234567890abcdefgh...`

#### Create PAT with read:packages permission
- Go to your GitHub account settings.
- Click on Developer settings.
- Click on Personal access tokens.
- Click on Generate new token (classic)
- Give your token a descriptive name.
- Select the read:packages scope.
- Click Generate token.