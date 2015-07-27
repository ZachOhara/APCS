package oharaShopping;

// Stuart Reges
// 3/28/07
//
// Class ShoppingFrame provides the user interface for a simple shopping
// program.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ShoppingFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ShoppingCart items;
	private JTextField total;

	public ShoppingFrame(Catalog products) {
		// create frame and order list
		this.setTitle(products.getName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.items = new ShoppingCart();

		// set up text field with order total
		this.total = new JTextField("$0.00", 12);
		this.total.setEditable(false);
		this.total.setEnabled(false);
		this.total.setDisabledTextColor(Color.BLACK);
		JPanel p = new JPanel();
		p.setBackground(Color.blue);
		JLabel l = new JLabel("order total");
		l.setForeground(Color.YELLOW);
		p.add(l);
		p.add(this.total);
		this.add(p, BorderLayout.NORTH);

		p = new JPanel(new GridLayout(products.size(), 1));
		for (int i = 0; i < products.size(); i++) {
			this.addItem(products.get(i), p);
		}
		this.add(p, BorderLayout.CENTER);

		p = new JPanel();
		this.add(this.makeCheckBoxPanel(), BorderLayout.SOUTH);

		// adjust size to just fit
		this.pack();
	}

	// Sets up the "discount" checkbox for the frame
	private JPanel makeCheckBoxPanel() {
		JPanel p = new JPanel();
		p.setBackground(Color.blue);
		final JCheckBox cb = new JCheckBox("qualify for discount");
		p.add(cb);
		cb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingFrame.this.items.setDiscount(cb.isSelected());
				ShoppingFrame.this.updateTotal();
			}
		});
		return p;
	}

	// adds a product to the panel, including a textfield for user input of
	// the quantity
	private void addItem(final Item product, JPanel p) {
		JPanel sub = new JPanel(new FlowLayout(FlowLayout.LEFT));
		sub.setBackground(new Color(0, 180, 0));
		final JTextField quantity = new JTextField(3);
		quantity.setHorizontalAlignment(SwingConstants.CENTER);
		quantity.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ShoppingFrame.this.updateItem(product, quantity);
				quantity.transferFocus();
			}
		});
		quantity.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				ShoppingFrame.this.updateItem(product, quantity);
			}
		});
		sub.add(quantity);
		JLabel l = new JLabel("" + product);
		l.setForeground(Color.white);
		sub.add(l);
		p.add(sub);
	}

	// When the user types a new value into one of the quantity fields,
	// parse the input and update the ShoppingCart. Display an error
	// message if text is not a number or is negative.
	private void updateItem(Item product, JTextField quantity) {
		// System.out.println("updating!");
		int number;
		String text = quantity.getText().trim();
		try {
			number = Integer.parseInt(text);
		} catch (NumberFormatException error) {
			number = 0;
		}
		if (number <= 0 && text.length() > 0) {
			Toolkit.getDefaultToolkit().beep();
			quantity.setText("");
			number = 0;
		}
		this.items.add(new ItemOrder(product, number));
		this.updateTotal();
	}

	// reset the text field for order total
	private void updateTotal() {
		double amount = this.items.getTotal();
		System.out.println(this.items);
		this.total.setText(NumberFormat.getCurrencyInstance().format(amount));
	}
}
