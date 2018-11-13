/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatprogramserver;

import java.net.Socket;

/**
 *
 * @author tuandung
 */
    abstract class SvRunnable implements Runnable {

        protected Socket cliSocket = null;

        public SvRunnable(Socket sk) {
            this.cliSocket = sk;
        }

    }