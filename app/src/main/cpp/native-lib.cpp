#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_fMarket_app_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF(
#ifdef MOCK
            "https://private-5de64-foodmarket1.apiary-mock.com/"
#endif
#ifdef STAGING
            ""
#endif
#ifdef PRODUCTION
            ""
#endif
    );
}
