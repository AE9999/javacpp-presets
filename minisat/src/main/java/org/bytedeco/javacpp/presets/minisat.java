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
               .put(new Info("Minisat::vec<Minisat::Lit>::Size").translate().valueTypes("int").pointerTypes("int"))
               .put(new Info("Minisat::IntSet<Minisat::Lit,Minisat::MkIndexLit>").cast().pointerTypes("LitMkIndexLitSet").define())
               .put(new Info("Minisat::vec<Minisat::Var,int>",
                             "Minisat::vec<int,int>",
                             "Minisat::vec<Minisat::Var>").pointerTypes("VarVecPointer").define())
               .put(new Info("Minisat::vec<Minisat::lbool,int>",
                             "Minisat::vec<Minisat::lbool>").pointerTypes("LboolVecPointer").define())
               .put(new Info("Minisat::vec<Minisat::Lit,int>",
                             "Minisat::vec<Minisat::Lit>").pointerTypes("LitVecPointer").define())
               .put(new Info("Minisat::RegionAllocator<uint32_t>::Ref",
                             "RegionAllocator<uint32_t>::Ref",
                             "Minisat::Ref").cast().valueTypes("long").pointerTypes("long").define())
               .put(new Info("Minisat::RegionAllocator<uint32_t>",
                             "RegionAllocator<uint32_t>")
                       .pointerTypes("RegionAllocatorPointer")
                       .define()
               )
                .put(new Info("Minisat::RegionAllocator<uint32_t>::Unit_Size",
                              "RegionAllocator<uint32_t>::Unit_Size")
                        .cppTypes("int")
                ) // This conversion is needed bycause otherwise Unit_Size enum is wrongly generated.
               .put(new Info("Minisat::ClauseAllocator").pointerTypes("ClauseAllocatorPointer").define())
               .put(new Info("Minisat::ClauseAllocator::Unit_Size").javaNames("ClauseAllocatorUnitSize"))
               .put(new Info("Minisat::ClauseAllocator::reloc").skip()) // Does not convert nicely but is not nes.
               //.put(new Info("Minisat::Clause").pointerTypes("ClausePointer").define())
               .put(new Info("Minisat::Clause::mark").skip()) // Does not convert nicley but is not nes.
               //.put(new Info("Minisat::ClauseIterator");
        ;
    }
}
