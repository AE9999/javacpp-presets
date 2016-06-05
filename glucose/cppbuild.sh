#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" gluecose
    popd
    exit
fi

mkdir -p $PLATFORM

cd $PLATFORM
git clone https://github.com/wadoon/glucose.git
cd glucose/
patch -p1 < ../../../glucose_source/hack.patch
mkdir build
cd build
cmake ../ -DCMAKE_INSTALL_PREFIX=`pwd`/../../ \
          -DCMAKE_CXX_FLAGS_RELEASE="-O3 -DNDEBUG -fPIC" \
          -DCMAKE_BUILD_TYPE=Release


make VERBOSE=1 install

cd ../..

