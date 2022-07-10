package com.sau.androidtts_stt

import android.app.Activity
import com.sau.androidtts_stt.translation_engine.translators.SpeechToTextConverter
import com.sau.androidtts_stt.translators.TextToSpeckConverter

/**
 *
 * This Factory class return object of TTS or STT depending on input enum TRANSLATORS
 */
class TranslatorFactory private constructor() {

    enum class TRANSLATORS {
        TEXT_TO_SPEECH,
        SPEECH_TO_TEXT
    }

    interface IConverter {
        fun initialize(message: String, appContext: Activity): IConverter

        fun getErrorText(errorCode: Int): String
    }


    fun with(TRANSLATORS: TRANSLATORS, conversionCallback: ConversionCallback): IConverter {
        return when (TRANSLATORS) {
            TranslatorFactory.TRANSLATORS.TEXT_TO_SPEECH -> TextToSpeckConverter(conversionCallback)

            TranslatorFactory.TRANSLATORS.SPEECH_TO_TEXT -> SpeechToTextConverter(conversionCallback)
        }
    }

    companion object {
        val instance = TranslatorFactory()
    }
}
