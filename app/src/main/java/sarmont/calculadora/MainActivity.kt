package sarmont.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

import androidx.appcompat.app.AppCompatViewInflater
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null
    var tvResultado: TextView? = null
    var ultimoNumero: Boolean = false
    var ultimoPonto: Boolean = false
    val sinalMenor: String = "-"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        tvResultado = findViewById(R.id.tvResultado)

        // FindViewById de todos os botões:
        var btnLimpar: Button? = findViewById(R.id.btnLimpar)
        val btnDivide: Button? = findViewById(R.id.btnDivide)
        val btnMultiplica: Button? = findViewById(R.id.btnMultiplica)
        val btnRemover: ImageButton? = findViewById(R.id.btnRemover)
        val btnNum7: Button? = findViewById(R.id.btnNum7)
        val btnNum8: Button? = findViewById(R.id.btnNum8)
        val btnNum9: Button? = findViewById(R.id.btnNum9)
        val btnMenos: Button? = findViewById(R.id.btnMenos)
        val btnNum4: Button? = findViewById(R.id.btnNum4)
        val btnNum5: Button? = findViewById(R.id.btnNum5)
        val btnNum6: Button? = findViewById(R.id.btnNum6)
        val btnMais: Button? = findViewById(R.id.btnMais)
        val btnNum1: Button? = findViewById(R.id.btnNum1)
        val btnNum2: Button? = findViewById(R.id.btnNum2)
        val btnNum3: Button? = findViewById(R.id.btnNum3)
        val btnIgual: Button? = findViewById(R.id.btnIgual)
        val btnNum0: Button? = findViewById(R.id.btnNum0)
        val btnPonto: Button? = findViewById(R.id.btnPonto)
        val btnSinal: Button? = findViewById(R.id.btnSinal)
        // --------------------- Final dos FindViewById ----------------------------


        // SetOnClickListener de todos os botões:
        btnLimpar?.setOnClickListener {
            tvInput?.text = ""
            tvResultado?.text = ""
        }

        btnDivide?.setOnClickListener {
            tvInput?.text?.let {

                if (ultimoNumero && !foiAdicionado(it.toString())) {
                    tvInput?.append("÷")
                    ultimoNumero = false
                    ultimoPonto = false
                }
            }
        }

        btnMultiplica?.setOnClickListener {
            tvInput?.text?.let {

                if (ultimoNumero && !foiAdicionado(it.toString())) {
                    tvInput?.append("×")
                    ultimoNumero = false
                    ultimoPonto = false
                }
            }
        }

        btnRemover?.setOnClickListener {
            val tamanho = tvInput?.length()
            if (tamanho != null) {
                if(tamanho > 0)
                    tvInput?.text = tvInput?.text?.subSequence(0,tamanho -1)
            }

        }

        btnNum7?.setOnClickListener {
            tvInput?.append("7")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum8?.setOnClickListener {
            tvInput?.append("8")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum9?.setOnClickListener {
            tvInput?.append("9")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnMenos?.setOnClickListener {
            tvInput?.text?.let {

                if (ultimoNumero && !foiAdicionado(it.toString())) {
                    tvInput?.append("-")
                    ultimoNumero = false
                    ultimoPonto = false
                }
            }
        }

        btnNum4?.setOnClickListener {
            tvInput?.append("4")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum5?.setOnClickListener {
            tvInput?.append("5")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum6?.setOnClickListener {
            tvInput?.append("6")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnMais?.setOnClickListener {
            tvInput?.text?.let {

                if (ultimoNumero && !foiAdicionado(it.toString())) {
                    tvInput?.append("+")
                    ultimoNumero = false
                    ultimoPonto = false
                }
            }
        }

        btnNum1?.setOnClickListener {
            tvInput?.append("1")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum2?.setOnClickListener {
            tvInput?.append("2")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnNum3?.setOnClickListener {
            tvInput?.append("3")
            ultimoNumero = true
            ultimoPonto = false
        }

        //Calculo das operações
        btnIgual?.setOnClickListener {
            if (ultimoNumero) {
                var tvValor = tvInput?.text.toString()
                var prefixo = ""
                try {
                    if (tvValor.startsWith("-")) {
                        prefixo = "-"
                        tvValor = tvValor.substring(1)
                    }
                    if (tvValor.contains("-")) {
                        val splitValue = tvValor.split("-")

                        var primeiro = splitValue[0] //Primeiro Valor
                        var segundo = splitValue[1] //Segundo Valor

                        if (prefixo.isNotEmpty()) {
                            primeiro = prefixo + primeiro
                        }

                        tvResultado?.text =
                            removerZeroAposPonto((primeiro.toDouble() - segundo.toDouble()).toString())

                    } else if (tvValor.contains("+")) {
                        val splitValue = tvValor.split("+")

                        var primeiro = splitValue[0] //Primeiro Valor
                        var segundo = splitValue[1] //Segundo Valor

                        if (prefixo.isNotEmpty()) {
                            primeiro = prefixo + primeiro
                        }

                        tvResultado?.text =
                            removerZeroAposPonto((primeiro.toDouble() + segundo.toDouble()).toString())
                    } else if (tvValor.contains("×")) {
                        val splitValue = tvValor.split("×")

                        var primeiro = splitValue[0] //Primeiro Valor
                        var segundo = splitValue[1] //Segundo Valor

                        if (prefixo.isNotEmpty()) {
                            primeiro = prefixo + primeiro
                        }

                        tvResultado?.text =
                            removerZeroAposPonto((primeiro.toDouble() * segundo.toDouble()).toString())
                    } else if (tvValor.contains("÷")) {
                        val splitValue = tvValor.split("÷")

                        var primeiro = splitValue[0] //Primeiro Valor
                        var segundo = splitValue[1] //Segundo Valor

                        if (prefixo.isNotEmpty()) {
                            primeiro = prefixo + primeiro
                        }

                        tvResultado?.text =
                            removerZeroAposPonto((primeiro.toDouble() / segundo.toDouble()).toString())

                    }
                } catch (e: ArithmeticException) {
                    e.printStackTrace()
                }
            }
        }

        btnNum0?.setOnClickListener {
            tvInput?.append("0")
            ultimoNumero = true
            ultimoPonto = false
        }

        btnPonto?.setOnClickListener {
            if (ultimoNumero && !ultimoPonto) {
                tvInput?.append(".")
                ultimoNumero = false
                ultimoPonto = true
                  }
        }

        btnSinal?.setOnClickListener {
            var tvValor = tvInput?.text.toString()
            tvInput?.text =
                if (tvValor.isNotEmpty() && tvValor.first() == sinalMenor.first()) {
                    tvValor.substring(1, tvValor.length)
                } else {
                    "$sinalMenor${tvValor}"
                }
            }
        }
    }

        // --------------------- Final dos setOnClickListener ----------------------------


    // Função para saber se o ultimo digito é um operador ou um número
    private fun foiAdicionado(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("÷")
                    || value.contains("+")
                    || value.contains("×")
                    || value.contains("-")
        }
    }

    private fun removerZeroAposPonto(resultado: String): String {
        var valor = resultado
        if (resultado.contains(".0"))
            valor = resultado.substring(0, resultado.length - 2)

        return valor
    }





















