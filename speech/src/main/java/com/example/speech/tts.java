package com.example.speech;


//Imports the Google Cloud client library
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.texttospeech.v1.AudioConfig;
import com.google.cloud.texttospeech.v1.SsmlVoiceGender;
import com.google.cloud.texttospeech.v1.SynthesisInput;
import com.google.cloud.texttospeech.v1.SynthesizeSpeechResponse;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.VoiceSelectionParams;
import com.google.protobuf.ByteString;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
* Google Cloud TextToSpeech API sample application.
* Example usage: mvn package exec:java
*                    -Dexec.mainClass='com.example.texttospeech.QuickstartSample'
*/
public class tts {

 /**
  * Demonstrates using the Text-to-Speech API.
  */
 tts(String str){
    // Instantiates a client
    try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
       // Set the text input to be synthesized
       SynthesisInput input = SynthesisInput.newBuilder()
             .setText(str)
             .build();

       // Build the voice request, select the language code ("en-US") and the ssml voice gender
       // ("neutral")
       VoiceSelectionParams voice = VoiceSelectionParams.newBuilder()
             .setLanguageCode("ko-KR")
             .setSsmlGender(SsmlVoiceGender.NEUTRAL)
             .build();

       // Select the type of audio file you want returned
       AudioConfig audioConfig = AudioConfig.newBuilder()
             .setAudioEncoding(AudioEncoding.LINEAR16)
             .build();

       // Perform the text-to-speech request on the text input with the selected voice parameters and
       // audio file type
       SynthesizeSpeechResponse response = textToSpeechClient.synthesizeSpeech(input, voice,
             audioConfig);

       // Get the audio contents from the response
       ByteString audioContents = response.getAudioContent();

       // Write the response to the output file.
       try (OutputStream out = new FileOutputStream("output.wav")) {
          try {
             out.write(audioContents.toByteArray());
          } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
          }
          System.out.println("Audio content written to file \"output.wav\"");
       } catch (FileNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       } catch (IOException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
       }
    } catch (IOException e2) {
       // TODO Auto-generated catch block
       e2.printStackTrace();
    }
 }
}