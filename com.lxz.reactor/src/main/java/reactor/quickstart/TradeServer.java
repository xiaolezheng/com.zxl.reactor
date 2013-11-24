package reactor.quickstart;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

import org.glassfish.grizzly.utils.LinkedTransferQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Jon Brisbin
 */
public class TradeServer {
	private static final Logger LOG = LoggerFactory.getLogger(TradeServer.class);
	final BlockingQueue<Order> buys = new LinkedTransferQueue<Order>();
	final BlockingQueue<Order> sells = new LinkedTransferQueue<Order>();
	final AtomicBoolean active = new AtomicBoolean(true);
	private final AtomicLong counter = new AtomicLong();
	private static final Random RANDOM = new Random();
	private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final int LEN = CHARS.length();
	private static final String[] SYMBOLS = new String[1000];

	static {
		for (int i = 0; i < SYMBOLS.length; i++) {
			SYMBOLS[i] = nextSymbol();
		}
	}

	private final Thread queueDrain = new Thread() {
		@Override
		public void run() {
			while (active.get()) {
				try {
					// Pull Orders off the queue and process them
					Order bOrder = buys.poll();
					Order sOrder = sells.poll();
					LOG.debug("border: "+bOrder.getTrade().getId());
					LOG.debug("sorder: "+sOrder.getTrade().getId());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	};

	public TradeServer() {
		queueDrain.start();
	}

	public Order execute(Trade trade) {
		Order o = new Order(counter.incrementAndGet()).setTrade(trade)
				.setTimestamp(System.currentTimeMillis());

		switch (trade.getType()) {
		case BUY:
			buys.add(o);
			break;
		case SELL:
			sells.add(o);
			break;
		}

		return o;
	}

	public Trade nextTrade() {
		return new Trade(counter.incrementAndGet())
				.setSymbol(SYMBOLS[RANDOM.nextInt(SYMBOLS.length)])
				.setQuantity(RANDOM.nextInt(500))
				.setPrice(
						Float.parseFloat(RANDOM.nextInt(700) + "."
								+ RANDOM.nextInt(99)))
				.setType((RANDOM.nextInt() % 2 == 0 ? Type.BUY : Type.SELL));
	}

	public void stop() {
		active.set(false);
	}

	private static String nextSymbol() {
		char[] chars = new char[4];
		for (int i = 0; i < 4; i++) {
			chars[i] = CHARS.charAt(RANDOM.nextInt(LEN));
		}
		return new String(chars);
	}

}
