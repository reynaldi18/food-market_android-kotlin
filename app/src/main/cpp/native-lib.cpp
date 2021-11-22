#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_movieApp_app_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF(
#ifdef MOCK
            "https://private-5de64-foodmarket1.apiary-mock.com/"
#endif
#ifdef STAGING
            "https://api.themoviedb.org/3/"
#endif
#ifdef PRODUCTION
            ""
#endif
    );
}
