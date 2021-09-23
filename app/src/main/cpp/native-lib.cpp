#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_segarmart_app_helper_JNIUtil_apiEndpoint(JNIEnv *env, jobject) {
    return env->NewStringUTF(
#ifdef MOCK
            "https://private-ca69f-segarmart.apiary-mock.com/"
#endif
#ifdef STAGING
            "http://167.172.28.124:91/"
#endif
#ifdef PRODUCTION
            "http://206.189.198.64/segarmart/public/"
#endif
    );
}
