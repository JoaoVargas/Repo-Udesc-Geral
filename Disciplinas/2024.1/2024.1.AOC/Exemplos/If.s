        addiu   $sp, $sp, -16
        sw      $ra, 12($sp)                    # 4-byte Folded Spill
        sw      $fp, 8($sp)                     # 4-byte Folded Spill
        move    $fp, $sp
        sw      $4, 4($fp)
        lw      $1, 4($fp)
        mul     $2, $1, $1
        move    $sp, $fp
        lw      $fp, 8($sp)                     # 4-byte Folded Reload
        lw      $ra, 12($sp)                    # 4-byte Folded Reload
        addiu   $sp, $sp, 16
        jr      $ra
        nop