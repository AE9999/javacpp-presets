package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;

/**
 * Created by ae on 5-6-16.
 */
@Properties(
        target="org.bytedeco.javacpp.glucose",
        value = @Platform(
                value = { "linux", "macosx", "linux-x86_64" },
                include = {
                        "glucose/mtl/Alloc.h",
                        "glucose/mtl/Vec.h",
                        "glucose/mtl/IntMap.h",
                        "glucose/core/SolverTypes.h",
                        "glucose/core/Solver.h",
                        "glucose/simp/Solver.h"
                },
                link    = { "glucose" }
        )
)
public class glucose {
}
