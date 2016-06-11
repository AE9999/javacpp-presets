package org.bytedeco.javacpp.presets;

import org.bytedeco.javacpp.annotation.*;
import org.bytedeco.javacpp.tools.*;

/**
 * Created by ae on 16-5-16.
 */
@Properties(
                target="org.bytedeco.javacpp.minisat",
                value = @Platform(
                                   value = { "linux", "macosx", "linux-x86_64" },
                                   define = { "MINISAT_CONSTANTS_AS_MACROS" },
                                   include = {
                                                "minisat/mtl/Alloc.h",
                                                "minisat/mtl/Vec.h",
                                                "minisat/mtl/IntMap.h",
                                                "minisat/core/SolverTypes.h",
                                                "minisat/core/Solver.h",
                                                "minisat/simp/SimpSolver.h"
                                             },
                                   link    = { "minisat" }
                                 )
            )
public class minisat implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("MINISAT_CONSTANTS_AS_MACROS").define())
               .put(new Info("defined(MINISAT_CONSTANTS_AS_MACROS)").define(true))
               .put(new Info("l_True", "l_False", "l_Undef").cppTypes("Minisat::lbool"))
               .put(new Info("UINT32_MAX").cppText("#define UINT32_MAX"))
               .put(new Info("_Size").translate().valueTypes("int").pointerTypes("int"))
               .put(new Info("Minisat::Lit").cppText("Lit"))
               .put(new Info("Minisat::Var").cast().valueTypes("int").pointerTypes("IntPointer", "IntBuffer", "int[]"))
               .put(new Info("vec<Minisat::Lit>::Size").translate().valueTypes("int").pointerTypes("int"))
               .put(new Info("Minisat::IntSet<Minisat::Lit,Minisat::MkIndexLit>").cast().pointerTypes("LitMkIndexLitSet").define())
               .put(new Info("Minisat::vec<Minisat::Var,int>",
                             "Minisat::vec<int,int>",
                             "vec<Minisat::Var>",
                             "Minisat::vec<Var,int>",
                             "vec<Var, int>").pointerTypes("VarVecPointer").define())
               .put(new Info("Minisat::vec<Minisat::lbool,int>",
                             "Minisat::vec<Minisat::lbool>",
                             "Minisat::vec<lbool,int>",
                             "Minisat::vec<lbool>").pointerTypes("LboolVecPointer").define())
               .put(new Info("Minisat::vec<Minisat::Lit,int>",
                              "vec<Minisat::Lit>",
                              "Minisat::vec<Lit,int>",
                              "vec<Lit,int>").pointerTypes("LitVecPointer").define())
               .put(new Info("Minisat::RegionAllocator<uint32_t>::Ref",
                             "RegionAllocator<uint32_t>::Ref",
                             "Ref").cast().valueTypes("long").pointerTypes("long").define())
               .put(new Info("Minisat::RegionAllocator<uint32_t>").pointerTypes("RegionAllocatorPointer"))
               .put(new Info("Minisat::ClauseAllocator", "::Minisat::ClauseAllocator").skip())
               .put(new Info("Minisat::ClauseIterator", "::Minisat::ClauseIterator").skip())
               .put(new Info("Minisat::ClauseAllocator::").skip())
               .put(new Info("Minisat::Clause", "::Minisat::Clause").skip())
        ;
    }
}
