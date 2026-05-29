[![Automatic version updates](https://github.com/zopencommunity/rcloneport/actions/workflows/bump.yml/badge.svg)](https://github.com/ZOSOpenTools/rcloneport/actions/workflows/bump.yml)

# rclone

rclone is a command-line tool for syncing files to and from cloud storage

# Installation and Usage

Use the zopen package manager ([QuickStart Guide](https://zopen.community/#/Guides/QuickStart)) to install:
```bash
zopen install rclone
```

# Example: Configure rclone for Box on z/OS using credentials from your laptop

## 1. Authenticate with Box on your laptop

On your laptop, run:

```sh
rclone config create box box client_id="" client_secret=""
```

Follow the browser-based login flow:

1. Sign in with SSO.
2. Grant rclone access to Box.
3. Confirm the remote configuration.

After authentication, view your rclone config:

```sh
rclone config show box
```

You should see something like:

```ini
[box]
type = box
client_id =
client_secret =
token = {"access_token":"YOUR_ACCESS_TOKEN","token_type":"bearer","refresh_token":"YOUR_REFRESH_TOKEN","expiry":"2026-05-26T12:48:28.638844-04:00","expires_in":3912}
```

Copy the full `[box]` configuration block.

---

## 2. Install rclone on z/OS

On z/OS, install rclone:

```sh
zopen install rclone -y
```

Find the rclone config file location:

```sh
rclone config file
```

Usually this will be:

```sh
~/.config/rclone/rclone.conf
```

Create the directory if it does not exist:

```sh
mkdir -p ~/.config/rclone
```

Open the config file:

```sh
vim ~/.config/rclone/rclone.conf
```

Paste the full `[box]` configuration block from your laptop:

```ini
[box]
type = box
client_id =
client_secret =
token = {"access_token":"YOUR_ACCESS_TOKEN","token_type":"bearer","refresh_token":"YOUR_REFRESH_TOKEN","expiry":"2026-05-26T12:48:28.638844-04:00","expires_in":3912}
```

Save and exit.

---

## 3. Test the Box remote on z/OS

List the top-level Box folders:

```sh
rclone lsd box:
```

List files recursively:

```sh
rclone ls box:
```

---

## 4. Sync a local directory to Box

To sync a local directory named `mydir` to Box:

```sh
rclone sync mydir box:mydir --progress
```

To copy instead of sync, use:

```sh
rclone copy mydir box:mydir --progress
```

`sync` makes the destination match the source and may delete files from Box that are not present locally.

`copy` only uploads new or changed files and is safer for first-time testing.


# Troubleshooting
* Does not support mounting
* Files are auto-converted from ASCII/UTF8
# Contributing
Contributions are welcome! Please follow the [zopen contribution guidelines](https://github.com/zopencommunity/meta/blob/main/CONTRIBUTING.md).
