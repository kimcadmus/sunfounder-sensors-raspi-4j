/**
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Marc Andreu
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package org.mandfer.sunfunpi4j;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.RaspiPin;
import static org.mandfer.sunfunpi4j.BaseSketch.wiringPiSetup;

/**
 *
 * @author marcandreuf
 */
public class Ex13_Button extends BaseSketch {
    private GpioPinDigitalInput btnPin;
    private GpioPinDigitalOutput ledPin;
   
    /**
     * @param gpio controller 
     */
    public Ex13_Button(GpioController gpio){
        super(gpio);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Ex13_Button sketch = new Ex13_Button(GpioFactory.getInstance());
        sketch.run(args);
    }
    
    @Override
    protected void setup(String[] args) {
        wiringPiSetup();
        btnPin = gpio.provisionDigitalInputPin(RaspiPin.GPIO_00);
        ledPin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        logger.debug("Button sensor ready!");        
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void loop(String[] args) {
        do{
          if(btnPin.isLow()){
              delayMilliseconds(10);
              if(btnPin.isLow()){
                  while(btnPin.isLow() && isNotInterrupted);
                  ledPin.toggle();
                  logger.debug("Button is pressed");
              }
          }  
        }while(isNotInterrupted);
    }
}
