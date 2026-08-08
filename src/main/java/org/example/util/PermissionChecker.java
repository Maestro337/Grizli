package org.example.util;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

public class PermissionChecker {

    public static boolean hasPermission(Member member, Permission permission) {
        return member.hasPermission(permission);
    }
}
