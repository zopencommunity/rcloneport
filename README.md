[![Automatic version updates](https://github.com/zopencommunity/rcloneport/actions/workflows/bump.yml/badge.svg)](https://github.com/ZOSOpenTools/rcloneport/actions/workflows/bump.yml)

# rclone

rclone is a command-line tool for syncing files to and from cloud storage

# Installation and Usage

Use the zopen package manager ([QuickStart Guide](https://zopen.community/#/Guides/QuickStart)) to install:
```bash
zopen install rclone
```

# Building from Source

1. Clone the repository:
```bash
git clone https://github.com/zopencommunity/rcloneport.git
cd rcloneport
```
2. Build using zopen:
```bash
zopen build -vv
```

See the [zopen porting guide](https://zopen.community/#/Guides/Porting) for more details.

# Documentation
Configure rclone for Box on z/OS using credentials from your laptop


Authenticate with Box on your laptop
rclone config create box box client_id="" client_secret=""

Follow the browser-based login flow:

Sign in with SSO.
Grant rclone access to Box.
Confirm the remote configuration.


After authentication, view your rclone config:
rclone config show box

You should see something like:
[box]
type = box
client_id =
client_secret =
token = {"access_token":"YOUR_ACCESS_TOKEN","token_type":"bearer","refresh_token":"YOUR_REFRESH_TOKEN","expiry":"2026-05-26T12:48:28.638844-04:00","expires_in":3912}Copy the full [box] configuration block.

2. Install rclone on z/OS

On z/OS, install rclone:
zopen install rclone -yFind the rclone config file location:
rclone config fileUsually this will be:
~/.config/rclone/rclone.confCreate the directory if it does not exist:
mkdir -p ~/.config/rcloneOpen the config file:
vim ~/.config/rclone/rclone.conf

Paste the full [box] configuration block from your laptop:
[box]
type = box
client_id =
client_secret =
token = {"access_token":"YOUR_ACCESS_TOKEN","token_type":"bearer","refresh_token":"YOUR_REFRESH_TOKEN","expiry":"2026-05-26T12:48:28.638844-04:00","expires_in":3912}Save and exit.

3. Test the Box remote on z/OS
List the top-level Box folders:
rclone lsd box:List files recursively:
rclone ls box:

4. Sync a local directory to Box
To sync a local directory named mydir to Box:
rclone sync mydir box:mydir --progressTo copy instead of sync, use:
rclone copy mydir box:mydir --progresssync makes the destination match the source and may delete files from Box that are not present locally. copy only uploads new or changed files and is safer for first-time testing.

# Troubleshooting

# Contributing
Contributions are welcome! Please follow the [zopen contribution guidelines](https://github.com/zopencommunity/meta/blob/main/CONTRIBUTING.md).
