
#!/usr/bin/env bash
if [ "$TRAVIS_PULL_REQUEST" == 'false' ]; then
    openssl aes-256-cbc -K $encrypted_2e8cf66e3aa7_key -iv $encrypted_2e8cf66e3aa7_iv -in .travis/codesigning.asc.enc -out .travis/codesigning.asc -d

    gpg --fast-import .travis/codesigning.asc
fi
