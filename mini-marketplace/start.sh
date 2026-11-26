#!/bin/bash

set -e

export DISPLAY=:99

Xvfb :99 -screen 0 1280x900x24 -nolisten tcp &
XVFB_PID=$!

for i in $(seq 1 30); do
    if xdpyinfo >/dev/null 2>&1; then
        break
    fi
    sleep 1
done

xdpyinfo >/dev/null 2>&1

x11vnc -display :99 \
    -forever \
    -shared \
    -nopw \
    -localhost \
    -rfbport 5900 &

VNC_PID=$!

websockify \
    --web=/usr/share/novnc \
    0.0.0.0:6080 \
    localhost:5900 &

WEB_PID=$!

trap 'kill "$XVFB_PID" "$VNC_PID" "$WEB_PID" 2>/dev/null || true' EXIT

mvn -B javafx:run
