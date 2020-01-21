package com.example.androidadvance3;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005\u00a2\u0006\u0002\u0010\u0002J1\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u001d\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u001eH\u0016\u00a2\u0006\u0002\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001b\u001a\u00020\fH\u0016J\u001c\u0010!\u001a\u0004\u0018\u00010\f2\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020%H\u0016JO\u0010&\u001a\u0004\u0018\u00010\'2\u0006\u0010\u001b\u001a\u00020\f2\u0010\u0010(\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u001e2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u001d\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u001e2\b\u0010)\u001a\u0004\u0018\u00010\u0005H\u0016\u00a2\u0006\u0002\u0010*J;\u0010+\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\"\u001a\u0004\u0018\u00010#2\b\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0010\u0010\u001d\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0005\u0018\u00010\u001eH\u0016\u00a2\u0006\u0002\u0010,R&\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\"\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016\u00a8\u0006."}, d2 = {"Lcom/example/androidadvance3/ContactProvider;", "Landroid/content/ContentProvider;", "()V", "CONTACT_PROJECTION", "Ljava/util/HashMap;", "", "", "getCONTACT_PROJECTION", "()Ljava/util/HashMap;", "setCONTACT_PROJECTION", "(Ljava/util/HashMap;)V", "CONTENT_URI", "Landroid/net/Uri;", "kotlin.jvm.PlatformType", "getCONTENT_URI", "()Landroid/net/Uri;", "setCONTENT_URI", "(Landroid/net/Uri;)V", "PROVIDER_NAME", "getPROVIDER_NAME", "()Ljava/lang/String;", "setPROVIDER_NAME", "(Ljava/lang/String;)V", "URL", "getURL", "setURL", "delete", "uri", "selection", "selectionArgs", "", "(Landroid/net/Uri;Ljava/lang/String;[Ljava/lang/String;)I", "getType", "insert", "values", "Landroid/content/ContentValues;", "onCreate", "", "query", "Landroid/database/Cursor;", "projection", "sortOrder", "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "update", "(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I", "Companion", "app_debug"})
public final class ContactProvider extends android.content.ContentProvider {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String PROVIDER_NAME;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String URL;
    private android.net.Uri CONTENT_URI;
    @org.jetbrains.annotations.NotNull()
    private java.util.HashMap<java.lang.String, java.lang.Integer> CONTACT_PROJECTION;
    public static final int CONTACT_KEY_INDEX = 0;
    public static final int CONTACT_ID_INDEX = 1;
    public static final com.example.androidadvance3.ContactProvider.Companion Companion = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPROVIDER_NAME() {
        return null;
    }
    
    public final void setPROVIDER_NAME(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getURL() {
        return null;
    }
    
    public final void setURL(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final android.net.Uri getCONTENT_URI() {
        return null;
    }
    
    public final void setCONTENT_URI(android.net.Uri p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.HashMap<java.lang.String, java.lang.Integer> getCONTACT_PROJECTION() {
        return null;
    }
    
    public final void setCONTACT_PROJECTION(@org.jetbrains.annotations.NotNull()
    java.util.HashMap<java.lang.String, java.lang.Integer> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.net.Uri insert(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    android.content.ContentValues values) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public android.database.Cursor query(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String[] projection, @org.jetbrains.annotations.Nullable()
    java.lang.String selection, @org.jetbrains.annotations.Nullable()
    java.lang.String[] selectionArgs, @org.jetbrains.annotations.Nullable()
    java.lang.String sortOrder) {
        return null;
    }
    
    @java.lang.Override()
    public boolean onCreate() {
        return false;
    }
    
    @java.lang.Override()
    public int update(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    android.content.ContentValues values, @org.jetbrains.annotations.Nullable()
    java.lang.String selection, @org.jetbrains.annotations.Nullable()
    java.lang.String[] selectionArgs) {
        return 0;
    }
    
    @java.lang.Override()
    public int delete(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.Nullable()
    java.lang.String selection, @org.jetbrains.annotations.Nullable()
    java.lang.String[] selectionArgs) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.String getType(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    public ContactProvider() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/example/androidadvance3/ContactProvider$Companion;", "", "()V", "CONTACT_ID_INDEX", "", "CONTACT_KEY_INDEX", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}