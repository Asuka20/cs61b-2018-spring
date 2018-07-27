import java.util.ConcurrentModificationException;
import java.util.Deque;

import synthesizer.GuitarString;

/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
public class GuitarHeroPiano {
    private static final String KEYBOARD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final double CONCERT_A = 440.0;

    public static void main(String[] args) {
        /* create keyboard-guitar strings */
        GuitarString[] strings = new GuitarString[KEYBOARD.length()];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = new GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0));
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index > 0) {
                    strings[index].pluck();
                }
            }

        /* compute the superposition of samples */
            double sample = 0;
            for (GuitarString s: strings) {
                sample += s.sample();
            }

        /* play the sample on standard audio */
            StdAudio.play(sample);

        /* advance the simulation of each keyboard-guitar string by one step */
            for (GuitarString s: strings) {
                s.tic();
            }
        }
    }
}

