#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" minisat
    popd
    exit
fi

mkdir -p $PLATFORM

cd $PLATFORM
unzip ../../minisat_source/master.zip
cd minisat-master
pwd
patch -p1 < ../../../minisat_source/hack.patch

mkdir build
cd build
export MINISAT_CONSTANTS_AS_MACROS=1
cmake ../ -DCMAKE_INSTALL_PREFIX=`pwd`/../../ \
          -DCMAKE_BUILD_TYPE=Release \
          -DCMAKE_CXX_FLAGS="-DMINISAT_CONSTANTS_AS_MACROS"
make VERBOSE=1 install

cd ../..

