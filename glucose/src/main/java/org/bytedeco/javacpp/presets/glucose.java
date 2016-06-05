package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

/**
 * Created by ae on 5-6-16.
 */
@Properties(
        target="org.bytedeco.javacpp.glucose",
        value = @Platform(
                value = { "linux", "macosx", "linux-x86_64" },
                include = {
                        "mtl/Alloc.h",
                        "mtl/Vec.h",
                        "mtl/Clone.h",
                        "core/SolverTypes.h",
                        "core/Solver.h",
                        "simp/SimpSolver.h"
                },
                link    = { "glucose" }
        )
)
public class glucose implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("l_True", "l_False", "l_Undef").cppTypes("Glucose::lbool"))
               .put(new Info("UINT32_MAX").cppText("#define UINT32_MAX"))
               .put(new Info("_Size").translate().valueTypes("int").pointerTypes("int"))
               .put(new Info("Glucose::Lit").cppText("Lit"))
               .put(new Info("Glucose::Var").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("vec<Glucose::Lit>::Size").translate().valueTypes("int").pointerTypes("int"))
               .put(new Info("Glucose::IntSet<Glucose::Lit,Glucose::MkIndexLit>").cast().pointerTypes("LitMkIndexLitSet").define())
               .put(new Info("Glucose::Clone").pointerTypes("ClonePointer").define())
               .put(new Info("Glucose::vec<Glucose::Lit>").pointerTypes("LitVecPointer").define())
               .put(new Info("Glucose::vec<Glucose::Var,int>",
                             "Glucose::vec<int,int>",
                             "vec<Glucose::Var>",
                             "Glucose::vec<Var,int>",
                             "vec<Var, int>").pointerTypes("VarVecPointer").define())
               .put(new Info("Glucose::vec<Glucose::lbool,int>",
                        "Glucose::vec<Glucose::lbool>",
                        "Glucose::vec<lbool,int>",
                        "Glucose::vec<lbool>").pointerTypes("LboolVecPointer").define())
               .put(new Info("Glucose::RegionAllocator<uint32_t>::Ref",
                        "RegionAllocator<uint32_t>::Ref",
                        "Ref").cast().valueTypes("long").pointerTypes("long").define())
               .put(new Info("Glucose::RegionAllocator<uint32_t>").pointerTypes("RegionAllocatorPointer"))
               .put(new Info("Glucose::ClauseAllocator", "::Glucose::ClauseAllocator").skip())
               .put(new Info("Glucose::ClauseIterator", "::Glucose::ClauseIterator").skip())
               .put(new Info("Glucose::ClauseAllocator::").skip())
               .put(new Info("Glucose::Solver::reduceDB").skip())
               .put(new Info("Glucose::reduceDB_lt").skip())
               .put(new Info("Glucose::Clause", "::Glucose::Clause").skip())
        ;
    }

}
